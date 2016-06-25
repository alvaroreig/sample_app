<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Http\Requests;
use App\Http\Requests\TicketFormRequest;
use App\Ticket;

class TicketsController extends Controller
{
	public function create()
	{
		return view('tickets.create');
	}

	public function store(TicketFormRequest $request)
	{
		$slug = uniqid();
		$ticket = new Ticket(array(
			'title' => $request->get('title'),
			'content' => $request->get('content'),
			'slug' => $slug
			));

		$ticket->save();

		return redirect('/contact')->with('status', 'Your ticket has been created! Its unique id is: '.$slug);
	}

	//GET /tickets
	public function index()
	{
		$tickets = Ticket::all();
		return view('tickets.index', compact('tickets'));
	}

	//GET /tickets/{slug}
	public function show($slug)
	{
		$ticket = Ticket::whereSlug($slug)->firstOrFail();
		return view('tickets.show', compact('ticket'));
	}
}
