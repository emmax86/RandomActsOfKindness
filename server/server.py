from flask import Flask
from subprocess import call

app = Flask(__name__)

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

    hashedGUID = hashGUID(request.json['guid']);

    registerObject = {
    'id': hashedGUID
    }

    # Do something with registerObject here

    return jsonify(registerObject), 201

import hashlib

def hashGUID(guid):
    hash = hashlib.sha256()
    hash.update(guid)
    hash.update("chips")
    return hash.hexdigest()

"""
"""

@app.route('/')
def hello_world():
    return 'Hello World!'

@app.route('/update-server', methods=['GET', 'POST'])
def update():
    call(["git pull"], shell=True)
    return 'Success!'


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=80, debug=True)

    """
    host='0.0.0.0', port=80, debug=True
    """
