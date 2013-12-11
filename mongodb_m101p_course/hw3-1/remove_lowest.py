
import pymongo
import sys

# establish a connection to the database
connection = pymongo.Connection("mongodb://localhost", safe=True)

# get a handle to the school database
db=connection.school
students = db.students


def find():

    print "find, reporting for duty"

    query = {}

    try:
        cursor = students.find(query).limit(10).skip(30)

    except:
        print "Unexpected error:", sys.exc_info()[0]

    # iterate through students
    for student in cursor:
        print student['_id']
        minimum_score = float("inf")
        # iterate through scores
        scores = student['scores']
        for single_score in scores:
            print single_score
            if single_score['type'] == 'homework':
                print 'it is a homework'
                if single_score['score'] < minimum_score :
                    minimum_score = single_score['score']

        

        

        


find()

