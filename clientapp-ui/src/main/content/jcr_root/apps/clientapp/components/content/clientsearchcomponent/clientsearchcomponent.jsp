<%@include file="/apps/clientapp/components/global.jsp"%>
<%@include file="/libs/foundation/global.jsp"%>
<bedrock:component className="com.icfi.clientapp.components.content.clientsearchcomponent.ClientSearchComponent" name="searchcomponent" />

<link href="/etc/clientlibs/clientapp/css/clientapp.css" rel="stylesheet" media="screen">

<!--
<style>
    #searchSelect{

        width: 300px;
        height: 300px;;
        border: 1px;
        background-color: lightcyan;
        border-radius: 10px;
        padding-top: 10px;
        padding-left: 20px;
        padding-bottom: 15px;
    }

    #searchById{

        display: none;
    }
    #searchByName{

        display: none;
    }
    #searchByIndustry{

        display: none;
    }
    #searchByAemVersion{

        display: none;
    }
    #submit{

        display: none;
    }
    #submitButton{
        width:50px;
        height:25px;

    }
    #resultTable{
        display: none;
    }


</style>
-->

<form id = "clientsForm" action="/bin/clientapp/clients" method = "post">
<div id ="searchSelect">
    <h2>Search Criteria</h2>
    Search Clients By:
    <select id = "searchType">
        <option selected=null>Slect</option>
        <option value ="All">Get All</option>
        <option value ="Id">Id</option>
        <option value ="Name">Name</option>
        <option value ="Industry">Industry</option>
        <option value ="AEM Version">AEM Version</option>

    </select></br></br>

<div id = "searchById">
    Enter Client Id Number:
    <input type="text" name = "id"/></br></br>
</div>

    <div id = "searchByName">
    Enter Client Name:
    <input type="text" name = "name"/></br></br>
    </div>
    <div id = "searchByIndustry">
        Enter Industry Type:
        <select id = "industry" name = "industry">
        <option disabled selected >--Slect--</option>
        <c:forEach var="industry" items="${searchcomponent.industryList}">
            <option value = "${industry}">${industry}</option>
        </c:forEach>
        </select></br></br>
    </div>
    <div id = "searchByAemVersion">
        Enter Aem Version:
       <select id = "aemVersion" name = "aemVersion">
           <option disabled selected >--Slect--</option>
           <c:forEach var="versionNumber" items="${searchcomponent.version}">
           <option value = "${versionNumber}">${versionNumber}</option>
           </c:forEach>
       </select></br></br>
    </div>



    <input type="hidden" name = "all" id = "all"/>
    <div id = "submit">
       <!-- <input type="submit" name = "submit" value = "Enter"/>-->
    <button type="button" name = "submitButton" id = "submitButton" value = "Enter">Enter</button>
    </div>
</br>
<div id = "result" ></div>
    <table id = "resultTable">
        <thead>
            <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Industry</th>
            </tr>
        </thead>
    </table>

</div>

</form>


<script type="text/javascript">
/*
    $( document ).ready(function() {

        $("#searchType").change(function() {

            switch($("#searchType").val()){

                case "All": $("#all").val("All");
                    $('#submit').show(); break;
                case "Id" :  $('#searchById').show();
                    $('#submit').show();break;
                case "Name" : $('#searchByName').show();
                    $('#submit').show();break;
                case "Industry" : $('#searchByIndustry').show();
                    $('#submit').show();break;
                case "AEM Version" : $('#searchByAemVersion').show();
                    $('#submit').show();break;

            }

        });

        $("#submitButton").click(function(){

            var form = $("#clientsForm");
            $.post( "/bin/clientapp/clients", form.serialize()).done(function(data) {

                var clients = $();
                $.each(data, function(i){
                    $.each(data[i], function(idx,obj){
                        clients = clients.add('<tr><td>' + obj.id + '</td><td>'+ obj.name+'</td><td>'+ obj.industry+'</td></tr>')
                        //$("#result").html(obj.name);
                    });

                    $("#resultTable").append(clients);

                    $("#resultTable").show();
                });
                //$("#result").html(data.name);

            });



        });
    });
*/
</script>
