new Vue({
    el: '#app',

    data: {
        todos: [],
        newTodo: {}
    },
    mounted: function () {
        this.loadTodos();
    },
    methods: {
        getCsrfHeaders() {
            const header = $("meta[name='_csrf_header']").attr("content");
            const token = $("meta[name='_csrf']").attr("content");
            let headers = {};
            headers[header] = token;
            return headers;
        },

        loadTodos() {
            let self = this;
            $.getJSON( "api/todos", function( data ) {
                self.todos = data
            });
        },

        saveTodo() {
            let self = this;

            $.ajax({
                type: "POST",
                url: 'api/todos',
                headers: self.getCsrfHeaders(),
                data: JSON.stringify(this.newTodo),
                contentType: "application/json",
                success: function() {
                    self.newTodo = {};
                    self.loadTodos();
                }
            });
        },

        deleteTodo(id) {
            let self = this;

            $.ajax({
                type: "DELETE",
                url: 'api/todos/'+id,
                headers: self.getCsrfHeaders(),
                success: function() {
                    self.loadTodos();
                }
            });
        }
    },
    computed: {

    }
});
