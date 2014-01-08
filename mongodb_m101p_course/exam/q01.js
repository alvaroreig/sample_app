use enron;

db.messages.aggregate([
	{$match:{ "headers.From":"andrew.fastow@enron.com"}}
	,{$unwind: "$headers.To"}
	,{$group:{_id:{"mail_to_id":"$headers.To"},"num_emails":{$sum:1}}}
])
