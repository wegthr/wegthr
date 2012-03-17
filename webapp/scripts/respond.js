$(function() {
    var gathering;
    var i=$.w.param('i');
    $.w.rest.gatheringByInvite(i,function(g) {
        gathering=g;
        $( "#gatheringTemplate" ).tmpl(gathering).appendTo( "#respond" );
        $('#goingBox').click(function() {
           alert(this.checked); 
        });
    });
   
});