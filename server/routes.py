from __init__ import app, db
from subprocess import call
from models import User, Data

from flask import request
from flask import abort
from flask import jsonify
from flask import json

@app.route('/register', methods=['POST'])
def register():
    if not request.json or not 'guid' in request.json:
        abort(400) # Malformed Packet
    guid = request.json['guid']
    user = User(guid)

    alreadyCreatedUser = User.query.filter_by(id=user.id).first()

    if alreadyCreatedUser:
        abort(403) # User already created

    db.session.add(user)
    db.session.commit()

    registerObject = {
    'id': user.id
    }

    return jsonify(registerObject), 201

@app.route('/phone', methods=['POST'])
def phone():
    if not request.json or (not ("call-time" in request.json)) or (not ('id' in request.json)):
        abort(400) # Malformed Packet

    matchingUser = User.query.filter_by(id=request.json['id']).first()

    if not matchingUser: #Check database for id to make sure it exists
        abort(401)

    secondsElapsed = request.json['call-time']
    matchingUser.add_phone_seconds(secondsElapsed)

    globalData = Data.query.all().first()
    globalData.incrementButtonClicks()
    db.session.add(globalData)

    userData = User.query.filter_by(id=request.json['id']).first()
    userData.incCall()
    db.session.add(userData)

    return "", 200

@app.route('/msg_to', methods=['POST'])
def msg_to():
    if not request.json or (not ("phone_number" in request.json)) or (not ('id' in request.json)):
        abort(400) # Malformed Packet

    userData = User.query.filter_by(id=request.json['id']).first()

    if not userData: #Check database for id to make sure it exists
        abort(401)

    globalData = Data.query.all().first()
    globalData.incrementButtonClicks()
    db.session.add(globalData)

    userData.incMessage()
    db.session.add(userData)
    db.session.add(userData)

    db.session.commit()

    return "", 200

@app.route('/donate', methods=['POST'])
def donate():
    if not request.json or (not ('donated' in request.json)) or (not ('id' in request.json)):
        abort(400) # Malformed Packet

    userData = User.query.filter_by(id=request.json['id']).first()

    if not user: #Check database for id to make sure it exists
        abort(401)

    userData.add_donation(request.json['donated'])

    globalData = Data.query.first()
    globalData.incDonate()
    db.session.add(globalData)

    userData.incDonate()
    db.session.add(userData)

    db.session.add(userData)
    db.session.commit()

    return "", 200

@app.route("/mail", methods=['POST'])
def mail():
    if not request.json or (not ('id' in request.json)):
        abort(400) # Malformed Packet

    userData = User.query.filter_by(id=request.json['id']).first()

    if not userData: #Check database for id to make sure it exists
        abort(401)

    globalData = Data.query.first()
    globalData.incMail()
    db.session.add(globalData)

    userData.incMail()
    db.session.add(userData)

    db.session.add(userData)
    db.session.commit()

    return "", 200

@app.route('/click_data', methods=['GET'])
def clickData():
    globalData = Data.query.first()
    donate = globalData.getDonate()
    call = globalData.getCall()
    message = globalData.getMessage()
    mail = globalData.getMail()
    return jsonify(call=call, post=message, donate=donate, mail=mail), 200

@app.route('/user_click_data', methods=['POST'])
def user_click_data():

    if not request.json or (not ('id' in request.json)):
        abort(400) # Malformed Packet

    userData = User.query.filter_by(id=request.json['id']).first()
    if not userData:
        abort(401)

    id = userData.getID()
    donate = userData.getDonate()
    call = userData.getCall()
    message = userData.getMessage()
    mail = userData.getMail()

    return jsonify(id=id, call=call, post=message, donate=donate, mail=mail), 200

@app.route('/get_data', methods=['GET'])
def get_data():
    return json.dumps([user.serialize() for user in User.query.all()]), 200

@app.route('/')
def landing_page():
    return 'Nothing seems to be here. Chips!'

@app.route('/update-server', methods=['GET', 'POST'])
def update():
    call(["git pull"], shell=True)
    return 'Success!'

@app.route('/remake-database', methods=['GET', 'POST'])
def remake():
    call(["rm webapp.db"], shell=True)
    from db import incClick
    incClick()
    return 'Database Remade!'

