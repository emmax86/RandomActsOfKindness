from flask import Flask
from subprocess import call

app = Flask(__name__)


@app.route('/')
def hello_world():
    return 'Hello World!'

@app.route('/update-server', methods=['GET', 'POST'])
def update():
    call(["git pull"], shell=True)
    return 'Success!'


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=80, debug=True)
