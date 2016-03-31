function getUser(name) {
	getUserGeneric(name, "v1/user/");
}

function getUserBdd(name) {
	getUserGeneric(name, "v1/userdb/");
}

function getUserGeneric(name, url) {
	$.getJSON(url + name, function(data) {
		afficheUser(data);
	});
}

function getForAll() {
	getSecure("v1/secure/forall");
}

function getByAnnotation() {
	getSecure("v1/secure/byannotation");
}

 function getSecure(url) {
 if($("#userlogin").val() != "") {
     $.ajax
     ({
       type: "GET",
       url: url,
       dataType: 'json',
       beforeSend : function(req) {
        req.setRequestHeader("Authorization", "Basic " + btoa($("#userlogin").val() + ":" + $("#passwdlogin").val()));
       },
       success: function (data) {
        afficheUser(data);
       },
       error : function(jqXHR, textStatus, errorThrown) {
       			alert('error: ' + textStatus);
       		}
     });
     } else {
     $.getJSON(url, function(data) {
     	    afficheUser(data);
        });
     }
 }

function postPlat(nom,cuisinier,quantitePart) {
    postPlatGeneric(nom,cuisinier,quantitePart,"v1/plat/");
}

function postUserBdd(name, alias, pwd) {
    postUserGeneric(name, alias, pwd, "v1/userdb/");
}

function postPlatGeneric(nom,cuisinier,quantitePart,url) {
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : url,
		dataType : "json",
		data : JSON.stringify({
			"nom" : nom,
			"cuisinier":cuisinier,
			"quantitePart":quantitePart,
			
		}),
		success : function(data, textStatus, jqXHR) {
			//affichePlat(data);
			afficheListPlat(data);
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('postUser error: ' + textStatus);
		}
	});
}

function listPlat() {
    listUsersGeneric("v1/plat/");
}

function listPlatBdd() {
    listUsersGeneric("v1/plat/");
}

function listUsersGeneric(url) {
	$.getJSON(url, function(data) {
		afficheListUsers(data)
	});
}

function affichePlat(data) {
	console.log(data);
	$("#reponse").html(data.id + " : <b>" + data.nom  +"</b>"+" : <b>" + data.cuisinier  +"</b>"+" : <b>" + data.quantitePart  +"</b>" );
}

function afficheListPlat(data) {
	var html = '<ul>';
	var index = 0;
	for (index = 0; index < data.length; ++index) {
		html = html + "<li>"+ data[index].name + "</li>";
	}
	html = html + "</ul>";
	$("#reponse").html(html);
}