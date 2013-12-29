use students;

db.hw53.aggregate([
	{$unwind: "$scores"}
	,{$match:{$or : [{"scores.type" : "homework"},{"scores.type" : "exam"}]}}
	,{$group:{_id:{"student_id":"$student_id","class_id":"$class_id"},"avg_scores":{"$avg":"$scores.score"}}}
	,{$group:{_id:{"class_id":"$_id.class_id"},"avg_scores":{"$avg":"$avg_scores"}}}
	// ,{$group:{_id:{"_id"}}
	// ,{$match:{$or : [{"_id.state" : "CA"},{"_id.state" : "NY"}]}}
	// ,{$match:{ "population":{$gt:25000}}}
	// ,{ $group:{ _id : "null", avgPop : { $avg : "$population" } } }
])