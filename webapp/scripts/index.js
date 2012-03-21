$(function() {
   
    (function setupForm() {
        $('#cf-button').click(function() {
          
            var payload, attendees=[];
            $('div#cf-attendees input[type="text"]').each(function(i,t) { attendees.push($(t).val()); });
            
            payload={
                'organizerEmail': $('input[name="organizerEmail"]').val(),
                'title': $('input[name="title"]').val(),
                'attendees': $('input[name="attendees"]').val().split(',')
            };
            $.post('rest/gathering/create.json', payload);
        });
    }());
   
});