<?php

use Illuminate\Database\Seeder;

class CatsTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
      DB::table('cats')->insert([
				['id' => 1, 'name' => "Berta",'date_of_birth' => new DateTime('2000-01-01'),'breed_id' => 1],
				['id' => 2, 'name' => "Tula",'date_of_birth' => new DateTime('2000-01-01'),'breed_id' => 2],
			]);
    }
}
