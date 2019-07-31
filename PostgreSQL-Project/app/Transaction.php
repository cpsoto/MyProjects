<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Transaction extends Model
{
    protected $table = 'transactions';
    
    public function equipment()
    {
        return $this->hasMany('App\Inventory', 'id');
    }

    public function user()
    {
        return $this->belongsTo('App\User');
    }
}
