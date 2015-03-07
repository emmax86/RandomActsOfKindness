from __init__ import db
from hashlib import sha256


class User(db.Model):
    id = db.Column(db.String(64), primary_key=True)
    guid = db.Column(db.String(36))
    donated = db.Column(db.Integer)
    messages = db.Column(db.Integer)
    phone_minutes = db.Column(db.DateTime)

    def __init__(self, guid):
        self.guid = guid
        self.id = sha256(guid).hexdigest()
        self.donated = 0
        self.messages = 0
        self.phone_minutes = 0

    def increment_messages(self):
        self.messages += 1

    def add_donation(self, amount):
        self.donated += amount

    def add_phone_minutes(self, amount):
        self.phone_minutes += amount
