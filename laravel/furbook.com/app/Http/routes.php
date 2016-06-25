<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the controller to call when that URI is requested.
|
*/

Route::get('/', function() {
	return redirect('cats');
});

Route::get('cats', function() {
	return 'All cats';
});

Route::get('cats/{id}', function($id) {
	sprintf('Cat #%d', $id);
})->where('id', '[0-9]+');

Route::get('about', function() {
	return view('about')->with('number_of_cats', 9000);
});