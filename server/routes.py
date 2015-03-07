from __init__ import app, db
from subprocess import call
from models import User
"""
"""

from flask import request
from flask import abort
from flask import jsonify

objects = []

@app.route('/create', methods=['POST'])
def create_object():
    if not request.json or not 'important' in request.json:
        abort(400)
    if len(objects) == 0:
        someobject = {
        'id': 0,
        'important': request.json['important'],
        'nonreq': request.json.get('nonreq', "no nonreq input"),
        }
    else:
        someobject = {
        'id': objects[-1]["id"] + 1,
        'important': request.json['important'],
        'nonreq': request.json.get('nonreq', "no nonreq input"),
        }

    objects.append(someobject)
    return jsonify({'object': someobject}), 201

@app.route('/register', methods=['POST'])
def register():
    if not request.json or not 'guid' in request.json:
        abort(400) # Malformed Packet
    guid = request.json['guid']
    user = User(guid)
    db.session.commit()

    registerObject = {
    'id': user.guid
    }

    # Do something with registerObject here

    return jsonify(registerObject), 201

import hashlib

@app.route('/phone', methods=['POST'])
def phone():
    if not request.json or (not ('call-time' in request.json)) or (not ('id' in request.json)):
        abort(400) # Malformed Packet

    if not True: #Check database for id to make sure it exists
        abort(401)

    # TODO Add calltime to the user that matches the id

    return "", 201

"""
"""

@app.route('/')
def hello_world():
    return 'Hello World!'

@app.route('/update-server', methods=['GET', 'POST'])
def update():
    call(["git pull"], shell=True)
    return 'Success!'
