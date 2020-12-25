new Vue({
    el: '#app',
    data: {
        products: [],
        promotions: []
    },
    created : function()
    {
        this.fetchProducts();
        this.authenticateUser();
    },
    methods: {
        fetchProducts: function() {
            $.ajax({
               // url: 'http://localhost:9191/products'
                url: '/ui/api/catalog/products'
            })
            .done(function(data) {
                this.products = data;
            }.bind(this));
        },
        authenticateUser: function() {
            var username = 'siva';
            var password = 'sivalabs';
            $.ajax({
                url: '/ui/api/authserver/oauth/token',
                datatype: 'json',
                type: 'post',
                headers: {'Authorization': 'Basic Y2xpZW50MTpjbGllbnRzZWNyZXQ='},
                async: false,
                data: {
                    scope: 'read_catalog',
                    username: username,
                    password: password,
                    grant_type: 'password'
                },
                success: function (data) {
                    //localStorage.setItem('token', data.access_token);
                    //success = true;
                    console.log('success', data);
                },
                error: function (e) {
                    //removeOauthTokenFromStorage();
                    console.log(e);
                }
            });


        }
    },
    computed: {

    }
});
