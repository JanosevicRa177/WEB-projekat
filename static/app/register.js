Vue.component("register", {
	data: function () {
		    return {
		      user: null
		    }
	},
	template: ` 
<div>
        <h4>Registracija na sajt:</h4>
        Username:
        <input type="text"></input>
        Password:
        <input type="password"></input>
        Name:
        <input type="text"></input>
        Surname:
        <input type="text"></input>
        Gender:
        <select name="gender" id="gender">
            <option value="male">Volvo</option>
            <option value="female">Saab</option>
            <option value="alien">Saab</option>
          </select>
        Birth Date:
        <input type="date" value="2000-05-15"></input>
    </div>	  
`
	, 
	methods : {
		init : function() {
			this.user = {};
		}, 
		clearSc : function () {
			if (confirm('Da li ste sigurni?') == true) {
				axios
		          .post('rest/proizvodi/clearSc')
		          .then(response => (this.init()))
			}
		} 
	},
	mounted () {
        axios
          .get('rest/proizvodi/getJustSc')
          .then(response => {
        	  this.sc = response.data;
          });
        axios
        .get('rest/proizvodi/getTotal')
        .then(response => (this.total = response.data));
    }
});