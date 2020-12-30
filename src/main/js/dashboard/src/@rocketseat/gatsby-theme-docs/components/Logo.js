import React from 'react';

export default function Logo(props) {
    return (
        <div style={{
            minWidth: '180px',
            minHeight: '34px',
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
        }} {...props}>
            <img src="https://pbs.twimg.com/profile_images/1324782944108511233/RCfLO8Yy_400x400.jpg"
                 alt="Logo"
                 width={60}
                 height={60}
            />
            <span style={{
                color: '#000000',
                fontSize: '1.4em',
                marginLeft: '8px',
            }}>おやさい鯖</span>
        </div>
    );
}