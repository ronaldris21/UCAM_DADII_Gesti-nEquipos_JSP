




<script type="text/javascript">
	$(document).ready(function() {
	  $('#confirmDeleteModal').on('shown.bs.modal', function(e) {
	    var urlDelete = $(e.relatedTarget).data('url-delete');
	    $('#deleteButton').click(function() {
	      window.location.href = urlDelete ;
	    });
	    
	    var mensaje = $(e.relatedTarget).data('datos');
	    $('#modal-content-message').text(mensaje)
	  });
	});

</script>

<script type="text/javascript">
var searchInput = document.getElementById("searchInput");
searchInput.addEventListener("keyup",(ev)=>{
  console.log("KEYUP");
    var tablerows = document.querySelectorAll("#tableBody tr");
    tablerows.forEach(function(row){
      var rowText = row.textContent.toLowerCase();
      if(rowText.includes(searchInput .value.toLowerCase()))
      {
        row.style.display = "";
      }
      else
      {
        row.style.display = "none";
      }
    })
})
</script>