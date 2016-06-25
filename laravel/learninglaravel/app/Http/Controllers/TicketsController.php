<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Http\Requests;
use App\Http\Requests\TicketFormRequest;

class TicketsController extends Controller
{
	public function create()
	{
		return view('tickets.create');
	}

	public function store(TicketFormRequest $request)
	{
		return $request->all();
	}
}
