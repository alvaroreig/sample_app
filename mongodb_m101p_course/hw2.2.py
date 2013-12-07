
import pymongo
import sys

# establish a connection to the database
connection = pymongo.Connection("mongodb://localhost", safe=True)

# get a handle to the school database
db=connection.students
grades = db.grades


def find():

    query = {'type':'homework'}
    sort_filter = [('student_id',pymongo.ASCENDING),('score',pymongo.ASCENDING)]

    try:
        cursor = grades.find(query).sort(sort_filter)
    except:
        print "Unexpected error:", sys.exc_info()[0]

    for doc in cursor:
        print doc
        


find()