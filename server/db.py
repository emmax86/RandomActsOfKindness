from __init__ import db

db.create_all()

from models import Data

chips = Data(0)
db.session.add(chips)
db.session.commit()



def resetdb():
    db.create_all()

    from models import Data

    chips = Data(0)
    db.session.add(chips)
    db.session.commit()