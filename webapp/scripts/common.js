$.extend({
    w: {
        params: function(){
            var vars = [], hash;
            var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
            for(var i = 0; i < hashes.length; i++)
            {
                hash = hashes[i].split('=');
                vars.push(hash[0]);
                vars[hash[0]] = hash[1];
            }
            return vars;
        },
        param: function(name){
            return $.params()[name];
        },
        rest: {
            config: {
                baseUrl: '../..',
                error: function(jqXHR, textStatus, errorThrown) {
                    alert(errorThrown);
                }
            },
            __call: function(type, url, data, cb) {
                url = this.config.baseUrl + '/rest/' + url + '.json';
                $.ajax({
                    'type': type,
                    'url': url,
                    'data': data,
                    'success': cb,
                    'error': this.config.error
                });
            },
            createGathering: function(gathering, cb) {
                this.__call('POST', 'gathering/create', gathering, cb);  
            },
            gathering: function(key, cb) {
                this.__call('GET', 'gathering/' + key, {}, cb);
            },
            gatheringByInvite: function(key, cb) {
                this.__call('GET', 'gathering/invite/' + key, {}, cb);
            }
        }
    }
});