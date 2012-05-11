$(function() {
    var gathering, inviteKey=$.w.param('i');
    if(!inviteKey) {
	window.location='/';
    } else {
	$.w.rest.gatheringByInvite(inviteKey,function(g) {
	    $('#respond h1').text(g.title);
	    var ob = $('#respond #organized-by');
	    ob.text(ob.text() + g.organizerEmail);
	    $('#respond #goingBox').click(function() {
		$.w.rest.vote(inviteKey, g.places[0].key, function(){});
	    })[0]
	    .checked = !!($(g.invitations).filter(function(j,k) {
		return k.key === inviteKey;
	    })[0].vote);
	});
    }
});