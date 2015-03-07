from subprocess import call
call(["rm webapp.db"], shell=True)

from __init__ import db

from models import Data

db.create_all()

chips = Data()
db.session.add(chips)
db.session.commit()


def incClick():
    db.create_all()

    chips = Data()
    db.session.add(chips)
    db.session.commit()