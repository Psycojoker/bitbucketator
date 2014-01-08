BitBucketator
=============

Quick and dirty CLI client arround (well, nearly nothing for the moment) BitBucket API.

Installation
============

    pip install git+https://github.com/Psycojoker/bitbucketator.git

Usage
=====

For the moment you need a config.py with 2 var USERNAME and PASSWORD. This sucks and need to be changed.

List repos:

    bb list  # fancy color when a repos is private
    bb list -s  # display scm (git/hg) url instead of http url

Create repos:

    bb create <list of names>

*-p* to make the repository public (private by default)
*-s hg* to use hg instead of git
