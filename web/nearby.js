var inner = "";
function typeChange()
{
  inner = "";
  var type = document.getElementById("type");
  var subtype = $("#subtype");
  chex(type, subtype);
}
function bartypeChange()
{
  inner = "";
  var type = document.getElementById("typebar");
  var subtype = $("#subtypebar");
  chex(type, subtype);
}

function chex(type, subtype)
{
	inner = "";
   if (type.value!="All")
  {
     subtype.css('visibility','visible');
     subtype.html(inner);
  }
  else
      subtype.css('visibility','hidden');

if (type.value=="Movies")
      subtype.css('visibility','hidden');

   if (type.value=="Sports")
 {
   inner += '<option value="All">Search All                      </option>'
   inner += '<option value="Tennis">Tennis</option>'
   inner += '<option value="Football">Football</option>'
   inner += '<option value="Baseball">Baseball</option>'
   inner += '<option value="Basketball">Basketball</option>'
   inner += '<option value="Volleyball">Volleyball</option>'
   inner += '<option value="Disc Golf">Disc Golf</option>'
   inner += '<option value="Golf">Golf</option>'
   inner += '<option value="Putt Putt">Putt Putt</option>'
   inner += '<option value="Bowling">Bowling</option>'
   inner += '<option value="Racquetball">Racquetball</option>'
   inner += '<option value="Other">Other</option>'
   subtype.html(inner);
 }
 else if (type.value=="Music")
 {
   inner += '<option value="All">Search All                      </option>'
   inner += '<option value="Concert">Concert</option>'
   inner += '<option value="Outdoor">Outdoor Performance</option>'
   inner += '<option value="Open Mic">Open Mic</option>'
   inner += '<option value="General Admission">General Admission</option>'
   inner += '<option value="Other">Other</option>'
   subtype.html(inner);
 }
 else if (type.value=="Sales")
 {
   inner += '<option value="All">Search All                      </option>'    
   inner += '<option value="Clothing">Clothing</option>'
   inner += '<option value="Shoe">Shoe</option>'
   inner += '<option value="Jewelry">Jewelry</option>' 
   inner += '<option value="Furniture">Furniture</option>'
   inner += '<option value="Appliance">Appliance</option>'
   inner += '<option value="Other">Other</option>'
   subtype.html(inner);
 }
 else if (type.value=="Parks")
 {
   inner += '<option value="All">Search All                      </option>'
   inner += '<option value="Tennis">Tennis</option>'
   inner += '<option value="Hiking">Hiking</option>'
   inner += '<option value="Gardens">Gardens</option>'
   inner += '<option value="Disc Golf">Disc Golf</option>'
   inner += '<option value="Playground">Playground</option>'
   inner += '<option value="Outdoor">Outdoor Performance</option>'
   inner += '<option value="Other">Other</option>'
   subtype.html(inner);
 }
 else if (type.value=="Arts")
 {
   inner += '<option value="All">Search All                      </option>'
   inner += '<option value="Museums">Museums</option>'
   inner += '<option value="Theater">Theater</option>'
   inner += '<option value="Plays">Plays</option>'
   inner += '<option value="Outdoor">Outdoor Performance</option>'
   inner += '<option value="General Admission">General Admission</option>'
   inner += '<option value="Other">Other</option>'
   subtype.html(inner);
 }
 else if (type.value=="Night Life")
 {
   inner += '<option value="All">Search All                      </option>'
   inner += '<option value="Bars">Bars</option>'
   inner += '<option value="Clubs">Clubs</option>'
   inner += '<option value="Open Late">Open Late</option>'
   inner += '<option value="General Admission">General Admission</option>'
   inner += '<option value="Other">Other</option>'
   subtype.html(inner);
 }
 else if (type.value=="Parties")
 {
   inner += '<option value="All">Search All                      </option>'
   inner += '<option value="Grand Opening">Grand Opening</option>'
   inner += '<option value="House Party">House Party</option>'
   inner += '<option value="Other">Other</option>'
   subtype.html(inner);
 }
 else if (type.value=="Other")
 {
   inner += '<option value="All">Search All                      </option>'    
   inner += '<option value="Grand Opening">Grand Opening</option>'
   inner += '<option value="Public Event">Public Event</option>'
   inner += '<option value="Other">Other</option>'
   subtype.html(inner);
 }
}