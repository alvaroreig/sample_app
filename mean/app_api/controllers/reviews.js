var mongoose = require('mongoose');
var Loc = mongoose.model('Location');

var sendJSONresponse = function(res, status, content) {
    res.status(status);
    res.json(content);
};

var doSetAverageRating = function(location) {
    var i, reviewCount, ratingAverage, ratingTotal;

    if (location.reviews && location.reviews.length > 0) {
        reviewCount = location.reviews.length;
        ratingTotal = 0;

        for (i = 0; i < reviewCount; i++) {
            ratingTotal = ratingTotal + location.reviews[i].rating;
        }

        ratingAverage = parseInt(ratingTotal / reviewCount, 10);
        location.rating = ratingAverage;
        location.save(function(err) {
            if (err) {
                console.log(err);
            } else {
                console.log("Average rating updated to", ratingAverage);
            }
        });
    }
};

var updateAverageRating = function(locationid) {
    Loc.findById(locationid).select('rating reviews').exec(function(err, location) {
        if (!err) {
            doSetAverageRating(location);
        }
    });
};

var doAddReview = function(req, res, location) {
    if (!location) {
        sendJSONresponse(res, 404, {
            "message": "locationid not found"
        });
    } else {
        console.log(req.body);
        location.reviews.push({
            author: req.body.author,
            rating: req.body.rating,
            reviewText: req.body.reviewText
        });

        location.save(function(err, location) {
            var thisReview;
            if (err) {
                sendJSONresponse(res, 400, err);
            } else {
                updateAverageRating(location._id);
                thisReview = location.reviews[location.reviews.length - 1];
                sendJSONresponse(res, 201, thisReview);
            }
        });
    }
};

module.exports.reviewsCreate = function(req, res) {
    var locationid = req.params.locationid;
    if (locationid) {
        Loc.findById(locationid).select('reviews').exec(function(err, location) {
            if (err) {
                sendJSONresponse(res, 400, err);
            } else {
                doAddReview(req, res, location);
            }
        });
    } else {
        sendJSONresponse(res, 404, {
            "message": "Not found, locationid required"
        });
    }
};

//Doesn't work.
module.exports.reviewsReadOne = function(req, res) {
  console.log("Getting single review");
  if (req.params && req.params.locationid && req.params.reviewid) {
    Loc
      .findById(req.params.locationid)
      .select('name reviews')
      .exec(
        function(err, location) {
          console.log(location);
          var response, review;
          if (!location) {
            sendJSONresponse(res, 404, {
              "message": "locationid not found"
            });
            return;
          } else if (err) {
            sendJSONresponse(res, 400, err);
            return;
          }
          if (location.reviews && location.reviews.length > 0) {
            console.log(location);
            console.log(req.params.reviewid);
            review = location.reviews.id(req.params.reviewid);
            console.log(review);
            if (!review) {
              sendJSONresponse(res, 404, {
                "message": "reviewid not found"
              });
            } else {
              response = {
                location: {
                  name: location.name,
                  id: req.params.locationid
                },
                review: review
              };
              sendJSONresponse(res, 200, response);
            }
          } else {
            sendJSONresponse(res, 404, {
              "message": "No reviews found"
            });
          }
        }
    );
  } else {
    sendJSONresponse(res, 404, {
      "message": "Not found, locationid and reviewid are both required"
    });
  }
};

module.exports.reviewsUpdateOne = function (req, res) { };

module.exports.reviewsDeleteOne = function (req, res) { };