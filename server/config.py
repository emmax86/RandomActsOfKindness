import os

basedir = os.path.abspath(os.path.dirname(__file__))
SQLALCHEMY_DATABASE_URI = 'mysql://root:arabsarerad@raok-db.cuj64aerqiwk.us-west-1.rds.amazonaws.com:330/raok'
SQLALCHEMY_MIGRATE_REPO = os.path.join(basedir, 'db_repository')
