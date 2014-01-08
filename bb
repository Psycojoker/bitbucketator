#!/usr/bin/env python

import sys
import argh
from bitbucket.bitbucket import Bitbucket

from config import USERNAME, PASSWORD

o = sys.stdout


def auth():
    return Bitbucket(USERNAME, PASSWORD)


def list():
    bb = auth()
    for i in sorted(bb.repository.all()[1], key=lambda x: x["name"]):
        if i["is_private"]:
            o.write("\033[0;35m")
        o.write(" %-28s" % i["name"])
        o.write("https://bitbucket.org/%s/%s" % (USERNAME, i["name"]))
        o.write("\033[0m\n")


parser = argh.ArghParser()
parser.add_commands([list, ])


if __name__ == '__main__':
    parser.dispatch()
