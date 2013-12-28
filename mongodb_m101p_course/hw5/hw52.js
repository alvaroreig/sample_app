use test;

db.zips.aggregate([
	{$match:{$or : [{"state" : "NY"},{"state" : "CA"}]}}
	// ,{$limit:5}
])