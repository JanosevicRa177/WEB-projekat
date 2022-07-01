Vue.component("showBuilding", {
	data: function () {
		    return {
				sportBuilding: "",
		    }
	},
	template: ` 
<div style="text-align:center;">
    <h1>{{this.sportBuilding.name}}</h1>
</div> 
`
	, 
	methods : {
		init : function() {
		}
	},
	mounted () {
		axios
		.get('sportBuilding/get',{params: {sportBuildingName: '' + this.$route.query.sportBuildingName}})
        .then(response => (this.sportBuilding = response.data));
    }
});