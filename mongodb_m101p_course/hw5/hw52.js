use test;

db.zips.aggregate([
	{$match:{$or : [{"state" : "NY"},{"state" : "CA"}]}}
	,{$group:{_id:"$city", "population":{"$sum":"$pop"}}}
	// ,{$limit:5}
])