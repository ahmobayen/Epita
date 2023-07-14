import React, { forwardRef } from "react";
import "./Post.css";
import Avatar from 'avataaars';
import { generateRandomAvatarOptions } from './avatar';

import DeleteIcon from '@material-ui/icons/Delete';
import EditIcon from '@material-ui/icons/Edit';

const Post = forwardRef(
  ({ displayName, text, personal, onClick }, ref) => {

    return (
      <div className="post" ref={ref}>
        <div className="post__avatar">
          <Avatar
            style={{ width: '100px', height: '100px' }}
            avatarStyle='Circle'
            {...generateRandomAvatarOptions() }
          />
        </div>
        <div className="post__body">
          <div className="post__header">
            <div className="post__headerText">
              <h3>
                {displayName}{" "}
              </h3>
            </div>
            <div className="post__headerDescription">
              <p>{text}</p>
            </div>
          </div>
          <div className="post__footer">
          {personal ? (
              <EditIcon fontSize="small" onClick={onClick}/>
            ) : ("")}
            {personal ? (
              <DeleteIcon fontSize="small" onClick={onClick}/>
            ) : ("")}
            
          </div>
        </div>
      </div>
    );
  }
);

export default Post;
