use test;

db.zips.aggregate([
	{$group:{_id:{"city":"$city","state":"$state"},"population":{"$sum":"$pop"}}}
	,{$match:{$or : [{"_id.state" : "CA"},{"_id.state" : "NY"}]}}
	,{$match:{ "population":{$gt:25000}}}
	,{ $group:{ _id : "null", avgPop : { $avg : "$population" } } }
])