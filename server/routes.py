from __init__ import app, db
from subprocess import call
from models import User

from flask import request
from flask import abort
from flask import jsonify

@app.route('/register', methods=['POST'])
def register():
    if not request.json or not 'guid' in request.json:
        abort(400) # Malformed Packet
    guid = request.json['guid']
    user = User(guid)
    db.session.add(user)
    db.session.commit()

    registerObject = {
    'id': user.guid
    }

    return jsonify(registerObject), 201

@app.route('/phone', methods=['POST'])
def phone():
    if not request.json or (not ('call-time' in request.json)) or (not ('id' in request.json)):
        abort(400) # Malformed Packet

    user = User.query.filter_by(id=request.json["id"]).first()

    if not user: #Check database for id to make sure it exists
        abort(401)

    # Todo Steve will do this

    return "", 201

@app.route('/')
def landing_page():
    return 'Nothing seems to be here'

@app.route('/update-server', methods=['GET', 'POST'])
def update():
    call(["git pull"], shell=True)
    return 'Success!'
