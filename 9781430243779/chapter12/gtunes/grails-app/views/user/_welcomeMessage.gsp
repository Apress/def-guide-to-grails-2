<div id="message notice">
    <div style="margin-top:20px">
        Welcome back 
        <span id="userFirstName">${session?.user?.firstName}!</span>
        <br><br>

        You have purchased 
       (${session.user.purchasedSongs?.size() ?: 0}) songs.
       <br>
    </div>
</div>

