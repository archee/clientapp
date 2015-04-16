<%@include file="/apps/clientapp/components/global.jsp"%>
<%@include file="/libs/foundation/global.jsp"%>
<bedrock:component className="com.icfi.clientapp.components.content.addclient.AddClient" name="client" />


<style>

    #addClient{

        width: 300px;
        height: 300px;;
        border: 1px;
        background-color: lightcyan;
        border-radius: 10px;
        padding-top: 10px;
        padding-left: 20px;
        padding-bottom: 15px;
    }

</style>



<div id = "addClient">

    <h2>Add New Client</h2>
<form id = "addClientForm">
    Client Name:
    <input type="text" name = "name"/></br></br>
    Client Since Date:
    <input type="text" name = "since"/></br></br>
    Industry:
    <input type="text" name = "industry"/></br></br>
    AEM Version:
    <input type="text" name = "aemVersion"/></br></br>

    <button type="button" name = "submitAddClient" id = "submitAddClient" value = "Enter">Enter</button>
</form>
</div>

<script type="text/javascript">

    $( document ).ready(function() {

        $("#submitAddClient").click(function () {

            var form = $("#addClientForm");
            $.post("/bin/clientapp/addclient", form.serialize()).done(function (data) {

                //alert("New Client Added");

            });
        });

    });

</script>