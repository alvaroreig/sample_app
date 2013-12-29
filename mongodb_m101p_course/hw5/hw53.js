use students;

db.hw53.aggregate([
	{$unwind: "$scores"}
	,{$match:{$or : [{"scores.type" : "homework"},{"scores.type" : "exam"}]}}
	,{$group:{_id:{"student_id":"$student_id","class_id":"$class_id"},"avg_scores":{"$avg":"$scores.score"}}}
	,{$group:{_id:{"class_id":"$_id.class_id"},"avg_scores":{"$avg":"$avg_scores"}}}
	,{$sort: {"avg_scores":-1}}
	,{$limit:1}
])