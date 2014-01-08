#!/usr/bin/env python

import argh
from os import path
from bitbucket.bitbucket import Bitbucket

from config import USERNAME, PASSWORD


def auth():
    return Bitbucket(USERNAME, PASSWORD)


def list(scm_url=False):
    bb = auth()
    for i in sorted(bb.repository.all()[1], key=lambda x: x["name"]):
        to_print = ""
        if i["is_private"]:
            to_print += "\033[0;35m"
        to_print += " %-28s" % i["name"]
        if scm_url:
            to_print += "ssh://hg@bitbucket.org/%s/%s" % (USERNAME, i["name"])
        else:
            to_print += "https://bitbucket.org/%s/%s" % (USERNAME, i["name"])
        to_print += "\033[0m"
        yield to_print


def create(public=False, scm="git", *names):
    if not names:
        names = [path.split(path.realpath(path.curdir))[1]]

    for name in names:
        bb = auth()
        bb.repository.create(name, private=not public, scm=scm)
        yield "Home page: https://bitbucket.org/%s/%s\n" % (USERNAME, name)
        if scm == "git":
            yield "git remote add origin ssh://hg@bitbucket.org/%s/%s" % (USERNAME, name)
            yield "git push -u origin master"
        else:
            yield "edit .hg/hgrc to add the repos then push"
            yield "hg push ssh://hg@bitbucket.org/%s/%s" % (USERNAME, name)


parser = argh.ArghParser()
parser.add_commands([list, create])


if __name__ == '__main__':
    parser.dispatch()
