use blog;

db.posts.aggregate([
	{$project:{_id:0,comments:1}}
	// ,{$limit:50}
	,{$unwind: "$comments"}
	,{$group : {_id:"$comments.author","num_comments":{$sum:1}}}
	,{$sort: {"num_comments":-1}}
	,{$limit:5}
])