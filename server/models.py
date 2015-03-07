from __init__ import db
from hashlib import sha256


class User(db.Model):
    id = db.Column(db.String(64), primary_key=True)
    guid = db.Column(db.String(36))
    donated = db.Column(db.Integer)
    messages = db.Column(db.Integer)
    phone_seconds = db.Column(db.Integer)
    mail = db.Column(db.Integer)

    def __init__(self, guid):
        self.guid = guid
        self.id = sha256(guid).hexdigest()
        self.donated = 0
        self.messages = 0
        self.phone_seconds = 0
        self.mail = 0

    def increment_messages(self):
        self.messages += 1

    def increment_mail(self):
        self.messages += 1

    def add_donation(self, amount):
        self.donated += amount

    def add_phone_seconds(self, amount):
        self.phone_seconds += amount

    def serialize(self):
        return {
            'guid': self.guid,
            'id': self.id,
            'donated': self.donated,
            'messages': self.messages,
            'phone_seconds': self.phone_seconds
        }


class Data(db.Model):
    buttonClicks = db.Column(db.Integer, primary_key=True)

    def __init__(self, globalButtonClicks):
        self.buttonClicks = globalButtonClicks

    def incrementButtonClicks(self):
        self.buttonClicks += 1

    def getButtonClicks(self):
        return self.buttonClicks