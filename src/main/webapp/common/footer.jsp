





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