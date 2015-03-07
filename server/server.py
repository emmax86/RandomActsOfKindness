from __init__ import app
from subprocess import call

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

"""
"""

@app.route('/')
def hello_world():
    return 'Hello World!'

@app.route('/update-server', methods=['GET', 'POST'])
def update():
    call(["git pull"], shell=True)
    return 'Success!'
