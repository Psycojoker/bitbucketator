#!/usr/bin/env python

import argh
from bitbucket.bitbucket import Bitbucket

from config import USERNAME, PASSWORD


def auth():
    return Bitbucket(USERNAME, PASSWORD)


def list():
    bb = auth()
    for i in sorted(bb.repository.all()[1], key=lambda x: x["name"]):
        to_print = ""
        if i["is_private"]:
            to_print += "\033[0;35m"
        to_print += " %-28s" % i["name"]
        to_print += "https://bitbucket.org/%s/%s" % (USERNAME, i["name"])
        to_print += "\033[0m"
        yield to_print


parser = argh.ArghParser()
parser.add_commands([list, ])


if __name__ == '__main__':
    parser.dispatch()
