<footer class="mainfooter">
  <div class="wrap">
    <ul class="foot-menu">
      <!-- <li><a href="">Link</a></li>
      <li><a href="">Link</a></li> -->
      <li class="foot-menu--logo"><a href=""><img src="/images/svg/logo.svg" alt=""></a></li>
      <!-- <li><a href="">Link</a></li>
      <li><a href="">Link</a></li> -->
    </ul>
    <div class="copy">2018 All rights reserved.</div>
  </div>
</footer>


<div id="modal-container">
  <div class="modal-wrap modal-add">
    <div class="modal-inner">
      <div class="modal-inner--header">
        Add <span class="item-name"></span>
      </div>
      <div class="modal-inner--content">
        <div class="inp-row">
          <label for="item-name">Enter <span class="item-name"></span> name</label>
          <input id="item-name" type="text" autofocus />
          
          <span id="addId" class="not-display"></span>	
          <span id="trigId" class="not-display"></span>

        </div>
        <span id="addPageContent" class="content-link">To add page content press: <a href="">Add Content</a></span>
        <button id="addItem" class="btn-modal">Add <span class="item-name"></span></button>

      </div>
    </div>
  </div>

  <div class="modal-wrap modal-edit">
    <div class="modal-inner">
      <div class="modal-inner--header">
        Edit <span class="item-name"></span>
      </div>
      <div class="modal-inner--content">
        <div class="inp-row">
          <label for="edit-item-name">Enter new <span class="item-name"></span> name</label>
          <input id="edit-item-name" type="text" value=""/>
          
		  <span id="editId" class="not-display"></span>	
		  <span id="trigId" class="not-display"></span>
        </div>

        <span id="editPageContent" class="content-link">To edit page content press: <a href="">Edit Content</a></span>

        <button id="editItem" class="btn-modal">Edit <span class="item-name"></span></button>

      </div>
    </div>
  </div>
  
   <div class="modal-wrap modal-update-password">
    <div class="modal-inner">
      <div class="modal-inner--header">
        <span>Update User Password</span>
      </div>
      <div class="modal-inner--content">
        <div class="inp-row">
          <label for="edit-item-name">Enter new password for the user: <span id="userEmail"></span></label>
          <input id="newPassword" type="text" value=""/>
          
		  <span id="updatePasswordId" class="not-display"></span>	
		  
        </div>

        <button id="updatePassword" class="btn-modal">Update Password</button>

      </div>
    </div>
  </div>

  <div class="modal-wrap modal-delete">
    <div class="modal-inner">
      <div class="modal-inner--header">
        Delete <span class="item-name"></span> Confirmation
      </div>
      <div class="modal-inner--content">
        <div class="inp-row">
          <p>Are you sure you want to delete <span class="item-name"></span> named:</br>
            <span class="delete-name"></span>?</p>

			<span id="deleteId" class="not-display"></span>
			<span id="pageName" class="not-display"></span>
        </div>
        <div class="modal-inner--buttons-two">
          <button id="deleteItem" class="btn-modal first-btn">Delete</button>
          <button class="btn-modal close-modal last-btn">Back</button>
        </div>
      </div>
    </div>
  </div>

  <div class="modal-wrap modal-share">
    <div class="modal-inner">
      <div class="modal-inner--header">
        Share <span class="item-name"></span> link
      </div>
      <div class="modal-inner--content">
        <div class="inp-row">

          <label for="item-link">The following share link is generated:</label>
          <input id="itemLink" type="text" readonly value=""/>
			
        </div>
          <button id="copyLink" class="btn-modal">Copy</button>
      </div>
    </div>
  </div>

  <div class="modal-wrap modal-new-page">
    <div class="modal-inner">
      <div class="modal-inner--header">
        New Page
      </div>
      <div class="modal-inner--content">
        <div class="inp-row">
          <label for="page-title">Enter page title</label>
          <input id="page-title" type="text" />
          
          <span id="newPageId" class="not-display"></span>	

        </div>
        <button id="saveTitle" class="btn-modal">Save</button>

      </div>
    </div>
  </div>

  <div class="modal-wrap modal-overview">
    <div class="modal-inner modal-inner-full">
      <div class="modal-inner--header" id="modal-page-title">
      	
      </div>
      <div class="modal-inner--content" id="modal-page-content">

      </div>
      
    </div>
    <button class="btn-slider display-left-middle btn-main-color" onclick="plusDivs(-1)">&#10094;</button>
	<button class="btn-slider display-right-middle btn-main-color" onclick="plusDivs(1)">&#10095;</button>
  </div>


</div>