from __init__ import db

db.create_all()

from models import Data

chips = Data(0)
db.session.add(chips)
db.session.commit()

def incClick():
    db.create_all()

    chips = Data(0)
    db.session.add(chips)
    db.session.commit()