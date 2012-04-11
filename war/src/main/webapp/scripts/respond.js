$(function() {
    var gathering, inviteKey=$.w.param('i');
    if(!inviteKey) {
	window.location='/';
    } else {
	$.w.rest.gatheringByInvite(inviteKey,function(g) {
	    gathering=g;
	    $('#gatheringTemplate').tmpl(gathering)
	    .appendTo('#respond')
	    .find('#goingBox').click(function() {
		$.w.rest.vote(inviteKey, g.places[0].key, function(){});
	    })[0]
	    .checked = !!($(gathering.invitations).filter(function(j,k) {
		return k.key === inviteKey;
	    })[0].vote);
	});
    }
});