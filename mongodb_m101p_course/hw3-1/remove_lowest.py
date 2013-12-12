
import pymongo
import sys

# establish a connection to the database
connection = pymongo.Connection("mongodb://localhost", safe=True)

# get a handle to the school database
db=connection.school
students = db.students


def find():

    print "find, reporting for duty"

    try:
        cursor = students.find()

    except:
        print "Unexpected error:", sys.exc_info()[0]

    # iterate through students
    for student in cursor:
        print student['_id']
        minimum_score = float("inf")
        minimum_score_position = -2

        # iterate through scores
        scores = student['scores']
        for position,item in enumerate(scores):
            print item
            if item['type'] == 'homework':
                print 'it is a homework'
                if item['score'] < minimum_score :
                    minimum_score = item['score']
                    print position
                    minimum_score_position = position

        
        print minimum_score
        print minimum_score_position
        scores.pop(minimum_score_position)
        print scores
        students.save(student)

find()

