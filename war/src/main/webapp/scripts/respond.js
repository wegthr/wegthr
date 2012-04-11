$(function() {
    var gathering, inviteKey=$.w.param('i');
    $.w.rest.gatheringByInvite(inviteKey,function(g) {
        gathering=g;
        var currentVote, tmpl = $('#gatheringTemplate').tmpl(gathering);
        tmpl.find('#goingBox').click(function() {
            var placeKey=$(this).data('placeKey');
            $.w.rest.vote(inviteKey, placeKey, function(){});
        });
        currentVote=$(gathering.invitations).filter(function(j,k) { return k.key === inviteKey; })[0].vote;
        $('.voteCheck').each(function(a,chk) {
            chk.checked=($(chk).data('placeKey') === currentVote);
        });
        
    });
   
});