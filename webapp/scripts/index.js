$(function() {
   
    (function setupForm() {
        $('#cf-button').click(function() {
          
            var attendees=[];
            $('div#cf-attendees input[type="text"]').each(function(i,t) { attendees.push($(t).val()); });
            
            var payload={
                'organizerEmail': $('input[name="organizerEmail"]').val(),
                'title': $('input[name="title"]').val(),
                'attendees': $('input[name="attendees"]').val().split(',')
            };
            $.post('rest/gathering.json', payload);
        });
    })();
   
});