
<!-- Modal de confirmación -->
<div class="modal " tabindex="-1" role="dialog" id="confirmDeleteModal">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Confirmar eliminación</h5>
      </div>
      <div class="modal-body">
      	
        ¿Estás seguro que deseas eliminar a <span id="modal-content-message" style="font-weight: bold;">  </span> ?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
        <button type="button" class="btn btn-danger" id="deleteButton">Eliminar</button>
      </div>
    </div>
  </div>
</div>



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
