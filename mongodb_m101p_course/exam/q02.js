use enron;

db.messages.aggregate([
	// {$match:{ "headers.From":"andrew.fastow@enron.com"}},
	{$group:{_id:{"mail_id":"$_id","mail_from_id":"$headers.From"},"mail_to_clean":{$addToSet:"$headers.To"}}}
	,{$unwind: "$mail_to_clean"}
	// ,{$limit:20}
	,{$group:{_id:{"mail_from_id":"$_id.mail_from_id","mail_to_id":"$mail_to_clean"},"num_emails":{$sum:1}}}
	// ,{$limit:20}
	,{$sort:{"num_emails":-1}}
	,{$limit:5}
])
