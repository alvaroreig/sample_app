
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

    previous_student_id = -1
    for doc in cursor:
        if (previous_student_id != doc['student_id']):
            print "student_id has changed"
            grades.remove(doc)
            previous_student_id = doc['student_id']
        print doc
        


find()